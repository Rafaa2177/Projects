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
package jobs4u.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;
import jobs4u.Application;
import jobs4u.costumerManager.domain.CostumerManager;
import jobs4u.costumerManager.repositories.CostumerManagerRepository;
import jobs4u.jobapplication.domain.JobApplication;
import jobs4u.jobopening.domain.JobOpening;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaCostumerManagerRepository
        extends JpaAutoTxRepository<CostumerManager, Long, Long>
        implements CostumerManagerRepository {

    public JpaCostumerManagerRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaCostumerManagerRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "id");
    }

    @Override
    public Optional<CostumerManager> findByEmail(EmailAddress email) {
        final TypedQuery<CostumerManager> q = createQuery("SELECT cm FROM CostumerManager cm INNER JOIN Jobs4uUser ju ON cm.user=ju WHERE ju.systemUser.email=:e ", CostumerManager.class);
        q.setParameter("e", email);
        return Optional.ofNullable(q.getResultList().get(0));
    }
}
