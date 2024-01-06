package edu.vku.searchjob.service;

import edu.vku.searchjob.entity.Cadidates;
import edu.vku.searchjob.entity.Employers;
import edu.vku.searchjob.entity.EmployersSearchCandidates;

import java.util.List;

public interface IEmployersSearchCandidatesService {
    public EmployersSearchCandidates addEmployersSearchCandidates(EmployersSearchCandidates employersSearchCandidates);
//    public List<EmployersSearchCandidates> listStatusFalse();
//    public List<EmployersSearchCandidates> listStatusTrue();
public List<EmployersSearchCandidates> listStatusFalse(Cadidates cadidates);
    public List<EmployersSearchCandidates> listStatusTrue(Cadidates cadidates);
    public void statusTrue(int id);
    public void statusfalse(int id);
    public List<EmployersSearchCandidates> listStatusAndEmployer(Employers employers);
    public List<EmployersSearchCandidates> listStatusAndEmployerTrue(Employers employers);
    public List<EmployersSearchCandidates> findByStatusAndCadidatesAndName(Cadidates canCadidates,String name);
    public List<EmployersSearchCandidates> findByStatusAndCadidatesTrueAndName(Cadidates cadidates,String name,String company);
}
