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

import jobs4u.Application;
import jobs4u.candidate.repositories.CandidateRepository;
import jobs4u.clientusermanagement.repositories.SignupRequestRepository;
import jobs4u.costumer.repositories.CostumerRepository;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.costumerManager.repositories.CostumerManagerRepository;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import jobs4u.jobs4uUser.repositories.Jobs4uUserRepository;
import jobs4u.ranking.repositories.RankingRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaClientUserRepository(autoTx);
    }

    @Override
    public JpaClientUserRepository clientUsers() {
        return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public Jobs4uUserRepository jobs4uUsers(TransactionalContext autoTx) {
        return new JpaJobs4uUserRepository(autoTx);
    }

    @Override
    public Jobs4uUserRepository jobs4uUsers() {
        return new JpaJobs4uUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CostumerManagerRepository costumerManagers(TransactionalContext autoTx) {
        return new JpaCostumerManagerRepository(autoTx);
    }

    @Override
    public CostumerManagerRepository costumerManagers() {
        return new JpaCostumerManagerRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public JobOpeningRepository jobopenings(TransactionalContext autoTx) {
        return new JpaJobOpeningRepository(autoTx);
    }

    @Override
    public JobOpeningRepository jobopenings() {
        return new JpaJobOpeningRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public JpaJobApplicationRepository jobapplications(TransactionalContext autoTx) {
        return new JpaJobApplicationRepository(autoTx);
    }

    @Override
    public JpaJobApplicationRepository jobapplications() {
        return new JpaJobApplicationRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public CostumerRepository costumers(TransactionalContext autoTx) {
        return new JpaCostumerRepository(autoTx);
    }

    @Override
    public CostumerRepository costumers() {
        return new JpaCostumerRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CandidateRepository candidates(TransactionalContext autoTx) {
        return new JpaCandidateRepository(autoTx);
    }

    @Override
    public CandidateRepository candidates() {
        return new JpaCandidateRepository(Application.settings().getPersistenceUnitName());
    }


    @Override
    public RankingRepository rankings(TransactionalContext autoTx) {
        return new JpaRankingRepository(autoTx);
    }

    @Override
    public RankingRepository rankings() {
        return new JpaRankingRepository(Application.settings().getPersistenceUnitName());
    }
}
