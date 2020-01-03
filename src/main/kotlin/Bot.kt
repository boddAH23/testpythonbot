import com.elbekD.bot.Bot
import com.elbekD.bot.server

fun main(args: Array<String>) {
    val token = System.getenv("TOKEN")
    val appName = System.getenv("HEROKU_APP_NAME")
    val bot = Bot.createWebhook(token) {
        url = "https://$appName.herokuapp.com"
        // below is optional parameters
        // certificate = Paths.get("<PATH TO CERTIFICATE>").toFile()
        // maxConnections = 20
        // allowedUpdates = listOf(AllowedUpdates.Message)
        // setWebhookAutomatically = true

        /*
            Jetty server is used to listen to incoming request from Telegram servers.
            Recommended way to use webhook is to set configured nginx as proxy server.
         */
         server {
           host = "0.0.0.0"

           port = (System.getenv("PORT") as Int?) ?: 8443

         }
    }

    bot.onCommand("/start") { msg, _ ->
        bot.sendMessage(msg.chat.id, "Hello World!")
    }

    bot.onCommand("/echo") { msg, opts ->
        bot.sendMessage(msg.chat.id, "${msg.text} ${opts ?: ""}")
    }

    bot.start()
}