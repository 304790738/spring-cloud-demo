import datetime
import logging
import random

from flask import Flask

from pyctuator.pyctuator import Pyctuator
# https://github.com/nacos-group/nacos-sdk-python
# https://nacos.io/zh-cn/docs/other-language.html
import nacos
# Keep the console clear - configure werkzeug (flask's WSGI web app) not to log the detail of every incoming request
logging.getLogger("werkzeug").setLevel(logging.WARNING)

my_logger = logging.getLogger("example")

app = Flask("Flask Example Server")
SERVER_ADDRESSES = "127.0.0.1"
NAMESPACE = "public"
data_id = "nacos.cfg.dataId"
group = "test"
# no auth mode
client = nacos.NacosClient(SERVER_ADDRESSES, namespace=NAMESPACE)


@app.route("/")
def hello():
    my_logger.debug(f"{datetime.datetime.now()} - {str(random.randint(0, 100))}")
    print("will return config from nacos")
    return client.get_config(data_id, group)


def regis_server_to_nacos():
    SERVER_ADDRESSES = "127.0.0.1"
    NAMESPACE = "public"

    # no auth mode
    # client = nacos.NacosClient(SERVER_ADDRESSES, namespace=NAMESPACE)
    # auth mode
    # client = nacos.NacosClient(SERVER_ADDRESSES, namespace=NAMESPACE, username="nacos", password="nacos")

    # get config
    # data_id = "nacos.cfg.dataId"
    # group = "test"
    print(client.get_config(data_id, group))

    # 注册实例
    client.add_naming_instance(service_name="bjtu-renji-ai", ip="127.0.0.1", port="5000")
    # 发送心跳
    client.send_heartbeat(service_name="bjtu-renji-ai", ip="127.0.0.1", port="5000")


if __name__ == '__main__':
    regis_server_to_nacos()
    app.run(port=5000, host="0.0.0.0", debug=True)
