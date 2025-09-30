/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package jobs4u.infrastructure.bootstrapers;

import java.util.Set;

import jobs4u.candidate.application.RegisterCandidateController;
import jobs4u.costumer.application.AddCostumerController;
import jobs4u.costumer.repositories.CostumerRepository;
import jobs4u.costumerManager.application.AddCostumerManagerController;
import jobs4u.costumerManager.domain.CostumerManager;
import jobs4u.costumerManager.repositories.CostumerManagerRepository;
import jobs4u.infrastructure.persistence.PersistenceContext;
import jobs4u.jobs4uUser.application.AddJobs4uUserController;
import jobs4u.jobs4uUser.domain.Jobs4uUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jobs4u.usermanagement.application.AddUserController;
import jobs4u.usermanagement.application.ListUsersController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class UsersBootstrapperBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    final AddUserController userController = new AddUserController();
    final AddJobs4uUserController jobs4uUserController = new AddJobs4uUserController();
    final AddCostumerManagerController costumerManagerController = new AddCostumerManagerController();
    final AddCostumerController costumerController = new AddCostumerController();
    final RegisterCandidateController candidateController = new RegisterCandidateController();
    final ListUsersController listUserController = new ListUsersController();

    private final CostumerRepository costumerRepository = PersistenceContext.repositories().costumers();
    private final CostumerManagerRepository costumerManagerRepository = PersistenceContext.repositories().costumerManagers();

    public UsersBootstrapperBase() {
        super();
    }

    /**
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     * @param roles
     */
    protected SystemUser registerUser(final String username, final String password, final String firstName,
            final String lastName, final String email, final Set<Role> roles) {
        SystemUser sysUser = null;
        try {
            sysUser = userController.addUser(username, password, firstName, lastName, email, roles);
            LOGGER.debug("»»» %s", username);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated user. let's just lookup that user
            sysUser = listUserController.find(Username.valueOf(username)).orElseThrow(() -> e);
        }
        return sysUser;
    }

    protected Jobs4uUser registerJob4uUser(final String username, final String password, final String firstName,
                                           final String lastName, final String email, final Set<Role> roles, String address, String phoneNumber) {
        Jobs4uUser jobs4uUser = null;
        try {
            jobs4uUser = jobs4uUserController.addJobs4uUser(username,password,firstName,lastName,email,roles, address, phoneNumber);
            System.out.println("»»» " + email);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return jobs4uUser;
    }

    protected void registerCostumerManager(Jobs4uUser user) {
        try {
            costumerManagerController.addCostumerManager(user);
            System.out.println("CM »»» " + user.getId());
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    protected void registerCustomer(Jobs4uUser jobs4uUser) {
        try {
            costumerController.addCostumer(jobs4uUser, "company1");
            CostumerManager manager = costumerManagerRepository.findAll().iterator().next();
            manager.addCustomer(costumerRepository.findAll().iterator().next());
            costumerManagerRepository.save(manager);
            System.out.println("C »»» " + jobs4uUser.getId());
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    protected void registerCandidate(final String username, final String password, final String firstName,
                                     final String lastName, final String email, final Set<Role> roles, String address, String phoneNumber) {
        try {
            candidateController.registerCandidate(username,password,firstName,lastName,email,roles,address,phoneNumber);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    


}
