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
package jobs4u.infrastructure.persistence;

import jobs4u.candidate.repositories.CandidateRepository;
import jobs4u.clientusermanagement.repositories.ClientUserRepository;
import jobs4u.clientusermanagement.repositories.SignupRequestRepository;
import jobs4u.costumer.repositories.CostumerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import jobs4u.jobapplication.repositories.JobApplicationRepository;
import jobs4u.jobopening.repositories.JobOpeningRepository;
import jobs4u.jobs4uUser.repositories.Jobs4uUserRepository;
import jobs4u.ranking.repositories.RankingRepository;
import jobs4u.costumerManager.repositories.CostumerManagerRepository;


/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the repositories
     *
     * @return
     */
    TransactionalContext newTransactionalContext();

    /**
     *
     * @param autoTx
     *            the transactional context to enrol
     * @return
     */
    UserRepository users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository users();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    ClientUserRepository clientUsers(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ClientUserRepository clientUsers();


    JobApplicationRepository jobapplications(TransactionalContext autoTx);

    JobApplicationRepository jobapplications();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    SignupRequestRepository signupRequests();



    Jobs4uUserRepository jobs4uUsers(TransactionalContext autoTx);

    Jobs4uUserRepository jobs4uUsers();

    CostumerManagerRepository costumerManagers(TransactionalContext autoTx);

    CostumerManagerRepository costumerManagers();

    CostumerRepository costumers(TransactionalContext autoTx);

    CostumerRepository costumers();

    CandidateRepository candidates();

    CandidateRepository candidates(TransactionalContext autoTx);

    JobOpeningRepository jobopenings(TransactionalContext autoTx);

    JobOpeningRepository jobopenings();

    RankingRepository rankings(TransactionalContext autoTx);

    RankingRepository rankings();
}
