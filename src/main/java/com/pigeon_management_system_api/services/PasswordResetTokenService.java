package com.pigeon_management_system_api.services;

import com.pigeon_management_system_api.config.AppException;
import com.pigeon_management_system_api.dto.ResetPasswordDTO;
import com.pigeon_management_system_api.model.PasswordResetToken;
import com.pigeon_management_system_api.model.User;
import com.pigeon_management_system_api.repository.PasswordResetTokenRepository;
import com.pigeon_management_system_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PasswordResetTokenService {

    private static final long EXPIRATION_MINUTES = 60;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    public void generateAndSendResetToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Błędny email", HttpStatus.NOT_FOUND));

        SecureRandom random = new SecureRandom();
        Integer token = 100000 + random.nextInt(900000);

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUserId(user.getId());
        resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES));

        passwordResetTokenRepository.save(resetToken);

        sendResetEmail(email, token);
    }

    private void sendResetEmail(String recipientEmail, Integer token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Moja Hodowla - Resetowanie hasła");
        message.setText("""
                Witaj!

                Otrzymałeś prośbę o resetowanie hasła w serwisie Moja Hodowla.
                Aby zresetować swoje hasło, użyj poniższego kodu:

                %s

                Kod będzie ważny przez 60 minut.
                Jeśli nie składałeś takiej prośby, zignoruj tę wiadomość.

                Pozdrawiamy,
                Zespół Moja Hodowla
                """.formatted(token));
        mailSender.send(message);
    }

    public Boolean resetPassword(ResetPasswordDTO resetPasswordDTO) {
        Boolean result = (Boolean) validatePasswordResetToken(resetPasswordDTO.getToken());

        if(result) {
            PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(resetPasswordDTO.getToken());
            User user = userRepository.findById(passwordResetToken.getUserId());
            user.setPassword(passwordEncoder.encode(CharBuffer.wrap(resetPasswordDTO.getNewPassword())));
            userRepository.save(user);
        }

        return result;
    }

    private boolean validatePasswordResetToken(Integer token) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);
        return resetToken != null && resetToken.getExpiryDate().isAfter(LocalDateTime.now());
    }
}
