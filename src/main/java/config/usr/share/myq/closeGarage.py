import asyncio

from aiohttp import ClientSession

from pymyq import login
from pymyq.account import MyQAccount
from pymyq.errors import MyQError, RequestError
from pymyq.garagedoor import STATE_CLOSED, STATE_OPEN
import yaml

with open("/usr/share/myq/myq.yml", "r") as stream:
        try:
                data = yaml.safe_load(stream)
        except yaml.YAMLError as exc:
                print(exc)

EMAIL = data['Email']
PASSWORD = data['Pass']


def closegarage():
	asyncio.get_event_loop().run_until_complete(close())


async def close() -> None:
	async with ClientSession() as websession:
		try:
			api = await login(EMAIL, PASSWORD, websession)
			for account in api.accounts.values():
				for idx, device in enumerate(account.covers.values()):
					try:
						close_task = await device.close(wait_for_state=False)
					except:
						pass
		except:
			pass

closegarage()
