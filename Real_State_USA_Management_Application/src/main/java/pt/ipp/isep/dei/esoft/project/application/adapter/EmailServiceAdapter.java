/**
 * The EmailServiceAdapter class is responsible for adapting the EmailService implementation based on the configuration file.
 * It provides methods to retrieve the EmailService instance.
 *
 * @author Rafael Lopes
 * @version 1.0
 * @since 2023-06-18
 */
package pt.ipp.isep.dei.esoft.project.application.adapter;

import pt.ipp.isep.dei.esoft.project.application.service.EmailService;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class EmailServiceAdapter {
    private static final String CONFIG_FILE_PATH = "src/main/resources/config/EmailService.config";
    private Properties properties = new Properties();

    /**
     * Constructs an instance of EmailServiceAdapter and loads the configuration file.
     * The configuration file path is defined by the CONFIG_FILE_PATH constant.
     *
     * @throws IllegalArgumentException If the configuration file is not found or cannot be read.
     */
    public EmailServiceAdapter() {
        File file = new File(CONFIG_FILE_PATH);

        try (InputStream is = new FileInputStream(file)) {
            properties.load(is);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("ERROR: The configuration file was not found.");
        } catch (IOException e) {
            throw new IllegalArgumentException("ERROR: Could not read the configuration file.");
        }
    }

    /**
     * Retrieves the default EmailService instance based on the configuration file.
     *
     * @return The default EmailService instance.
     * @throws IllegalArgumentException If there is an error instantiating the EmailService implementation.
     */
    public EmailService getEmailService() {
        return getEmailService("email.service");
    }

    /**
     * Retrieves the EmailService instance based on the specified type.
     *
     * @param type The type of the EmailService implementation.
     * @return The EmailService instance.
     * @throws IllegalArgumentException If there is an error instantiating the EmailService implementation
     *                                  or if the specified type is not found in the configuration file.
     */
    public EmailService getEmailService(String type) {
        String className = properties.getProperty(type);

        EmailService service;
        try {
            service = (EmailService) Class.forName(className)
                    .getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("ERROR: Could not instantiate Email Service.");
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("ERROR: Email service implementation is not accessible.");
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException("ERROR: Email Service Implementation was not found.");
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("ERROR: Email Service Implementation was not found.");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("ERROR: Email Service Implementation was not found.");
        }

        return service;
    }

}
