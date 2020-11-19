import datetime
import logging
import random

from flask import Flask
from nacos import NacosClient

from pyctuator.pyctuator import Pyctuator
# https://github.com/nacos-group/nacos-sdk-python
# https://nacos.io/zh-cn/docs/other-language.html
import nacos
# Keep the console clear - configure werkzeug (flask's WSGI web app) not to log the detail of every incoming request
logging.getLogger("werkzeug").setLevel(logging.WARNING)

my_logger = logging.getLogger("example")

app = Flask("Flask Example Server")


@app.route("/")
def hello():
    my_logger.debug(f"{datetime.datetime.now()} - {str(random.randint(0, 100))}")
    print("Printing to STDOUT")
    return "Hello World!"


example_app_address = "127.0.0.1"
example_sba_address = "127.0.0.1"

Pyctuator(
    app,
    "huiche-ai",
    app_url=f"http://{example_app_address}:5000",
    pyctuator_endpoint_url=f"http://{example_app_address}:5000/pyctuator",
    registration_url=f"http://{example_sba_address}:8848/instances",
    app_description="Demonstrate Spring Boot Admin Integration with Flask",
)
def regis_server_to_nacos():
    SERVER_ADDRESSES = "127.0.0.1"
    NAMESPACE = "public"

    # no auth mode
    client = nacos.NacosClient(SERVER_ADDRESSES, namespace=NAMESPACE)
    # auth mode
    # client = nacos.NacosClient(SERVER_ADDRESSES, namespace=NAMESPACE, username="nacos", password="nacos")

    # get config
    data_id = "nacos.cfg.dataId"
    group = "test"
    print(client.get_config(data_id, group))

    # 注册实例
    client.add_naming_instance("bjtu-renji-ai", "127.0.0.1", "5000")
    # 发送心跳
    client.send_heartbeat("bjtu-renji-ai", "127.0.0.1", "5000")
if __name__ == '__main__':
    regis_server_to_nacos()
    app.run(port=5000, host="0.0.0.0", debug=True)
