package com.tonypepe.data

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.io.File

data class ServerConfig(
    var site_title: String = "",
    var site_author: String = "",
    var git_repo_url: String = "",
    var author_github_url: String = "",
    var author_facebook_url: String = "",
    var author_twitter_url: String = "",
) {
    companion object {
        val instance by lazy {
            Yaml(Constructor(ServerConfig::class.java))
                .load<ServerConfig>(
                    File("config.yaml").bufferedReader()
                )
        }
    }
}