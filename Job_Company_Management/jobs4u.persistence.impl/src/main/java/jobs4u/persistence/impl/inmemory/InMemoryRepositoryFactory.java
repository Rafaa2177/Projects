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
package jobs4u.persistence.impl.inmemory;

import jobs4u.candidate.repositories.CandidateRepository;
import jobs4u.clientusermanagement.repositories.ClientUserRepository;
import jobs4u.clientusermanagement.repositories.SignupRequestRepository;
import jobs4u.costumer.repositories.CostumerRepository;
import jobs4u.infrastructure.bootstrapers.BaseBootstrapper;
import jobs4u.infrastructure.persistence.RepositoryFactory;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;
import jobs4u.costumerManager.repositories.CostumerManagerRepository;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import jobs4u.jobs4uUser.repositories.Jobs4uUserRepository;
import jobs4u.ranking.repositories.RankingRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public ClientUserRepository clientUsers(final TransactionalContext tx) {

        return new InMemoryClientUserRepository();
    }

    @Override
    public ClientUserRepository clientUsers() {
        return clientUsers(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public Jobs4uUserRepository jobs4uUsers(TransactionalContext autoTx) {
        return  new InMemoryJobs4uUserRepository();
    }

    @Override
    public Jobs4uUserRepository jobs4uUsers() {
        return jobs4uUsers(null);
    }

    @Override
    public CostumerManagerRepository costumerManagers(TransactionalContext autoTx) {
        return new InMemoryCostumerManagerRepository();
    }

    @Override
    public CostumerManagerRepository costumerManagers() {
        return costumerManagers(null);
    }

    @Override
    public JobOpeningRepository jobopenings(TransactionalContext autoTx) {
        return new InMemoryJobOpeningRepository();
    }

    @Override
    public JobOpeningRepository jobopenings() {
        return jobopenings(null);
    }


    @Override
    public JobApplicationRepository jobapplications(TransactionalContext autoTx) {
        return new InMemoryJobApplicationRepository();
    }

    @Override
    public JobApplicationRepository jobapplications() {
        return jobapplications(null);
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

    @Override
    public CostumerRepository costumers(TransactionalContext autoTx) {
        return new InMemoryCostumerRepository();
    }

    @Override
    public CostumerRepository costumers() {
       return costumers(null);
    }
    @Override
    public CandidateRepository candidates(TransactionalContext autoTx) {
        return new InMemoryCandidateRepository();

    }

    @Override
    public CandidateRepository candidates() {
        return candidates(null);
    }

    @Override
    public RankingRepository rankings(TransactionalContext autoTx) {
        return new InMemoryRankingRepository();
    }

    @Override
    public RankingRepository rankings() {
        return rankings(null);
    }
}
