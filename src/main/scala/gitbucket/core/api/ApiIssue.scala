package gitbucket.core.api

import gitbucket.core.model.Issue
import gitbucket.core.util.RepositoryName

import java.util.Date


/**
 * https://developer.github.com/v3/issues/
 */
case class ApiIssue(
  number: Int,
  title: String,
  user: ApiUser,
  labels: List[ApiLabel],
  state: String,
  created_at: Date,
  updated_at: Date,
  body: String)(repositoryName: RepositoryName, isPullRequest: Boolean){
  val url = ApiPath(s"/api/v3/repos/${repositoryName.fullName}/issues/${number}")
  val labels_url = ApiPath(s"/api/v3/repos/${repositoryName.fullName}/issues/${number}/labels{/name}")
  val comments_url = ApiPath(s"/api/v3/repos/${repositoryName.fullName}/issues/${number}/comments")
  val html_url = ApiPath(s"/${repositoryName.fullName}/${if(isPullRequest){ "pull" }else{ "issues" }}/${number}")
}

object ApiIssue{
  def apply(issue: Issue, repositoryName: RepositoryName, user: ApiUser, labels: List[ApiLabel] = Nil): ApiIssue =
    ApiIssue(
      number = issue.issueId,
      title  = issue.title,
      user   = user,
      labels = labels,
      state  = if(issue.closed){ "closed" }else{ "open" },
      body   = issue.content.getOrElse(""),
      created_at = issue.registeredDate,
      updated_at = issue.updatedDate)(repositoryName, issue.isPullRequest)
}
