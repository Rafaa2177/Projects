package jobs4u.ranking.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.candidate.domain.Candidate;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.ranking.domain.Ranking;

import java.util.List;


public interface RankingRepository extends DomainRepository<Long,Ranking> {

    List<Candidate> findByJobOpening(JobOpening jobOpening);

    List<Ranking> findRankingByJobOpening(JobOpening jobOpening);

    int countForExistingJobOpening(JobOpening jobOpening);

    boolean findByJobOpeningAndRanking(JobOpening jobOpening, int ranking);
}
