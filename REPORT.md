# Lab 1 Git Race -- Project Report

## Description of Changes
1. **Added messages properties files**
   - Created messages.properties, messages_es.properties and messages_fr.properties.
   - Each file contains the translation for the default message 'Hello, ...!'
   - Default language is English, with Spanish and French as supported languages.
2. **Added message source configuration**
    - Created the config package and inside of it the MessageConfig.kt.
    - Defined a messageSource bean that loads messages*.properties files.
    - Used UTF-8 encoding to support accents and special characters.
    - Used setFallbackToSystemLocale(false) in order to avoid using language server's locale.
3. **Updated HelloController:**
    - Modified both controllers to accept an optional lang parameter.
    - If lang is not provided, the original behaviour is preserved.
4. **Adapted HellControllerUnitTests:**
    - Updated the unit tests to provide a messageSource when creating both controllers.
    - Added null as default for the lang, to keep the test with their original behaviour.

## Technical Decisions
1. **Internationalization (i18n):**
    I decided to create a messageSource bean instead of programming directly on application.properties, due to the fact 
    that this option provides more flexibility, and in a future you can extend the configuration with more facility if 
    you want, like different basename or integer with another messageSource.
2. **Encoding:**
    The encoding was set in UTF-8 to provide support for special characters and accents.
3. **System locale fallback:**
    Although setFallbackToSystemLocale(false) is deprecated in newer Spring versions, the alternative 
    (isFallbackToSystemLocale) is protected and not accessible in Kotlin. This option prevents using language server 
    configuration.

## Learning Outcomes
- Learned how to configure internationalization in SpringBoot using Kotlin.
- Understood how @Bean works in order to register a reusable object in the SpringBoot context application.

## AI Disclosure
### AI Tools Used
- ChatGPT (OpenAI GPT-5)

### AI-Assisted Work
1. **What was generated with AI assistance:**
    - AI suggested the idea of create a MessageConfig.kt instead of handle messages configuration in the
      application.properties.
    - AI provided guidance on the use of setFallBackToSystemLocale(false).
    - AI provided explanation of how setFallBackToSystemLocale(false) works.
    - AI proposed the core code to implement in the HelloController the lang parameter.
2. **Percentage of AI-assisted vs. original work:**
3. **Any modifications made to AI-generated code:**
    - The code of the HelloController that AI gave me was modified to keep the structure of the code that was given 
    to us.

### Original Work
1. **Work done without AI assistance:**
    - Search for the message.properties method and how to implement it.
    - Manually created messages.properties, messages_es.properties, messages_fr.properties.
    - Manually created the MessageConfig.kt file inside the correct package.
    - Used IntelliJ IDEA autocompletion to complete most of the MessageConfig.kt
    - Manually added comments in order to explain how the code works and its characteristics.
    - Manually modified the HelloApiController taking the example o Hello Controller.
    - Manually modified HelloControllerUnitTests in order to add the language parameter in the controllers constructors.
2. **My understanding and learning process:**
    - Learned how the original web code works and search for information about multi-language support in SpringBoot.
    - Learned how the properties files works and learn what a @Bean was.
    - Learned to extend controllers and implement new functionality without breaking existing expectations.
    - Learned to modify existing tests without breaking existing expectations.
    