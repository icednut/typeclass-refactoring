package io.icednut.study

import cats.Applicative
import cats.effect.{IO, IOApp}
import cats.implicits._
import org.kohsuke.github.GHIssueState

class RepositoryRank[F[_] : Applicative : FromGithub] {

  def getPoint(repositoryName: String): F[Int] =
    for {
      github <- FromGithub[F].connect
    } yield {
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
