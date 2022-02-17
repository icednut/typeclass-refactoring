package io.icednut.study

import cats.Applicative
import cats.effect.{IO, IOApp}
import org.kohsuke.github.{GHIssueState, GitHub}

class RepositoryRank[F[_] : Applicative] {

  def getPoint(repositoryName: String): F[Int] = Applicative[F].pure {
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

object RepositoryRank extends IOApp.Simple {

  override def run: IO[Unit] = {
    val spring = new RepositoryRank[IO]

    for {
      point <- spring.getPoint("icednut/typeclass-refactoring")
      _ <- IO.println(point)
    } yield ()
  }
}
