package ru.stqa.pft.github;

import com.google.common.collect.ImmutableBiMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import javax.json.JsonObject;
import java.io.IOException;

public class GithubTests {
  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("6a25c63471f5fb571afb697659b40126e3962d68");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("anastasia", "Ovchinnickova2016")).commits();
    for (RepoCommit commit: commits.iterate(new ImmutableBiMap.Builder<String, String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
