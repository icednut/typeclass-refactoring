package io.icednut.study

import cats.Id
import cats.effect.IO
import org.kohsuke.github.GitHub

trait FromGithub[F[_]] {
  def connect: F[GitHub]
}

object FromGithub {

  def apply[F[_] : FromGithub]: FromGithub[F] = implicitly[FromGithub[F]]

  implicit def githubForIo: FromGithub[IO] = {
    new FromGithub[IO] {
      override def connect: IO[GitHub] = IO.delay(GitHub.connectAnonymously())
    }
  }

  implicit def githubForMock: FromGithub[Id] = {
    new FromGithub[Id] {
      override def connect: Id[GitHub] = {
        ???
      }
    }
  }
}
