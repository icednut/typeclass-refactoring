package io.icednut.study

import org.kohsuke.github.{GHIssueState, GitHub}

class RepositoryRank {

  def getPoint(repositoryName: String): Int = {
    val github = GitHub.connect()
    val repository = github.getRepository(repositoryName)

    val issuePoint = if (repository.hasIssues) 1 else 0
    val readmePoint = if (repository.getReadme() != null) 1 else 0
    val prPoint = if (repository.getPullRequests(GHIssueState.CLOSED).size > 0) 1 else 0
    val starPoint = repository.getStargazersCount
    val forkPoint = repository.getForksCount

    issuePoint + readmePoint + prPoint + starPoint + forkPoint
  }
}

object RepositoryRank {

  def main(args: Array[String]): Unit = {
    val spring = new RepositoryRank
    val point = spring.getPoint("whiteship/live-study")

    println(point)
  }
}
