package help.lixin.starlink.plugin.gitlab.service;

import help.lixin.starlink.plugin.gitlab.dto.repository.BranchDTO;

import java.util.List;

public interface IRepositoryService {
    List<BranchDTO> getBranches(String projectIdOrPath, String instanceCode);
}
