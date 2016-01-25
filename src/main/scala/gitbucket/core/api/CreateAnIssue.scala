package gitbucket.core.api

/**
  * https://developer.github.com/v3/issues/#create-an-issue
  * api form
  */
case class CreateAnIssue (
    title: String,
    body: Option[String],
    assignee: Option[String],
    milestone: Option[Int],
    labels: Any
){
  def isValid: Boolean = {
    title.nonEmpty
  }
}
