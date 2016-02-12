package gitbucket.core.api

/**
  * https://developer.github.com/v3/issues/#create-an-issue
  * api form
  */
case class CreateAnIssue (
    title: String,
    body: Option[String],
    assignee: Option[String],
    state: Option[String],
    milestone: Option[Int],
    labels: Option[List[String]]
){
  def isValid: Boolean = {
    title.nonEmpty
  }
  def isValidChange: Boolean = {
    title.nonEmpty &&
      (state.isEmpty || state.get.matches("[open|closed]"))
  }
}
