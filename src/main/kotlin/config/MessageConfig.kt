package es.unizar.webeng.hello.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource

/**
 * Configuration for internalization support (i18n).
 *
 * Defines a message source that loads messages.properties files
 * and other variants like message_es.properties or
 * message_fr.properties.
 */
@Configuration
class MessageConfig {

    /**
     * Bean that search and load messages.properties and their variants
     * in order to have different translations for different languages.
     *
     * - encoding = UTF-8: supports special characters like ñ or accents
     * - fallbackToSystemLocale = false: it uses de default language defined
     * in locale instead of the system language.
     */
    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasename("messages")
        messageSource.setDefaultEncoding("UTF-8")
        messageSource.setFallbackToSystemLocale(false)
        return messageSource
    }
}