package changchanghwang.parrotworldserver.common.mail

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(private val javaMailSender: JavaMailSender) {
    fun sendEmail(
        to: String,
        subject: String,
        text: String,
        bcc: String? = null,
    ) {
        val mail = javaMailSender.createMimeMessage()
        val messageHelper = MimeMessageHelper(mail, "UTF-8")
        messageHelper.setFrom(System.getenv("MAIL_HOST"))
        messageHelper.setTo(to)
        messageHelper.setSubject(subject)
        messageHelper.setText(text, true)
        bcc?.let { messageHelper.setBcc(it) }
        javaMailSender.send(mail)
    }
}
