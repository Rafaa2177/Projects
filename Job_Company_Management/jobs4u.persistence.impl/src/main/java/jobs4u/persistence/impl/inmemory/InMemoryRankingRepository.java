package jobs4u.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.candidate.domain.Candidate;
import jobs4u.jobopening.domain.JobOpening;
import jobs4u.ranking.domain.Ranking;
import jobs4u.ranking.repositories.RankingRepository;

import java.util.LinkedList;
import java.util.List;

public class InMemoryRankingRepository 
        extends InMemoryDomainRepository<Ranking, Long>
        implements RankingRepository {

    @Override
    public List<Candidate> findByJobOpening(JobOpening jobOpening) {
        System.out.println("Finding candidates by job opening " + jobOpening);
        List<Candidate> candidatesList = new LinkedList<>();
        match(e -> e.getCandidateApplication().jobopening().equals(jobOpening)).iterator().forEachRemaining(e -> candidatesList.add(e.getCandidateApplication().candidate()));
        return candidatesList;
    }

    @Override
    public int countForExistingJobOpening(JobOpening jobOpening) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countForExistingJobOpening'");
    }

    @Override
    public boolean findByJobOpeningAndRanking(JobOpening jobOpening, int ranking) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByJobOpeningAndRanking'");
    }

    @Override
    public List<Ranking> findRankingByJobOpening(JobOpening jobOpening) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findRankingByJobOpening'");
    }
}
