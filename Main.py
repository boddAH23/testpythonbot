import datetime
import os

from testbot import BotHandler

token = os.environ.get('TOKEN', None)

greet_bot = BotHandler("1036743508:AAGWl55n0XDuwhvVcgO2HbE0PINpB9qiUBA")
greetings = ('hello', 'hi', 'greetings', 'sup')
now = datetime.datetime.now()


def main():
    new_offset = None

    while True:
        greet_bot.get_updates(new_offset)

        last_update = greet_bot.get_last_update()

        last_update_id = last_update['update_id']
        last_chat_id = last_update['message']['chat']['id']
        last_chat_name = last_update['message']['chat']['first_name']

        greet_bot.send_message(last_chat_id, 'Hallo  {}'.format(last_chat_name))

        new_offset = last_update_id + 1

if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        exit()
