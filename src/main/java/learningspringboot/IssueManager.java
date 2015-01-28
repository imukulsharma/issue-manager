package learningspringboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.github.api.GitHubIssue;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.stereotype.Service;

@Service
public class IssueManager {
	String githubToken = "f1b671760dfd5e43f3de6278b62e023102328fa5";
	String org = "spring-projects";
	String[] repos = new String[] { "spring-boot", "spring-boot-issues" };

	GitHubTemplate gitHubTemplate = new GitHubTemplate(githubToken);

	public List<Issue> findOpenIssues() {
		List<Issue> openIssues = new ArrayList<Issue>();

		for (String repo : repos) {
			List<GitHubIssue> issues = gitHubTemplate.repoOperations().getIssues(org, repo);

			for (GitHubIssue issue : issues) {
				if (issue.getState().equals("open")) {
					openIssues.add(new Issue(repo, issue));
				}
			}
		}

		return openIssues;
	}

}
