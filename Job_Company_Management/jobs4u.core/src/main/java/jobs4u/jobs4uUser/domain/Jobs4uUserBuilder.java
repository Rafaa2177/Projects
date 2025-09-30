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
package jobs4u.jobs4uUser.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jobs4u.usermanagement.domain.BaseRoles;

import java.util.Calendar;
import java.util.Set;

/**
 * A factory for User entities.
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class Jobs4uUserBuilder implements DomainFactory<Jobs4uUser> {

    private String address;
    private String phone;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Role> roles;
    private final UserManagementService userSvc = AuthzRegistry.userService();



    public Jobs4uUserBuilder withInfo(final String username, final String password, final String firstName,
                              final String lastName,
                              final String email, final Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        return this;
    }

    public Jobs4uUserBuilder withAddress(final String address) {
        this.address = address;
        return this;
    }

    public Jobs4uUserBuilder withPhone(final String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public Jobs4uUser build() {
        SystemUser systemUser = userSvc.registerNewUser(username, password, firstName, lastName, email, roles);
        if(address == null) {
            return new Jobs4uUser(systemUser, this.phone);
        }
        return new Jobs4uUser(systemUser, this.address, this.phone);
    }
}
