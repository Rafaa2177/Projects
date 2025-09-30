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
package jobs4u.infrastructure.bootstrapers.demo;

import java.util.HashSet;
import java.util.Set;


import jobs4u.infrastructure.bootstrapers.UsersBootstrapperBase;
import jobs4u.jobs4uUser.domain.Jobs4uUser;
import jobs4u.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 *
 * @author Paulo Sousa
 */
public class ClientUserBootstrapper extends UsersBootstrapperBase implements Action {

    @SuppressWarnings("squid:S2068")
    private static final String PASSWORD1 = "Password1";

    @Override
    public boolean execute() {
        registerCustomer("1221157@isep.ipp.pt", PASSWORD1, "Johny", "Cash", "1221157@isep.ipp.pt", "Somewhere", "123456789");
        registerCandidate("janedoe@email.com", PASSWORD1, "Jane", "Doe", "janedoe@email.com", "Somewhere", "123456789");
        registerCandidate("1221139@isep.ipp.pt", PASSWORD1, "Tiago", "Dong", "1221139@isep.ipp.pt", "Somewhere", "123456789");
        return true;
    }

    private void registerCustomer(final String username, final String password, final String firstName,
                                    final String lastName, final String email, final String address, final String phoneNumber) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CUSTOMER);
        roles.add(BaseRoles.CLIENT_USER);

        Jobs4uUser jobs4uUser = registerJob4uUser(username,password,firstName,lastName,email,roles, address, phoneNumber);
        registerCustomer(jobs4uUser);
    }

    private void registerCandidate(final String username, final String password, final String firstName,
                                  final String lastName, final String email, final String address, final String phoneNumber) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CANDIDATE);
        roles.add(BaseRoles.CLIENT_USER);

        registerCandidate(username,password,firstName,lastName,email,roles,address,phoneNumber);
    }
}
