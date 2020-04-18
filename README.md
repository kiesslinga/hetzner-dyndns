# Hetzner DynDNS
This project aims to provide a DynDNS service for hetzner domains.
So when you have a domain at https://www.hetzner.com/ you can use a subdomain as DynDNS to your home network.

# How to configure
Inside the repository there lies the `application.properties` *(/src/main/resources/application.properties)* file where you configure the dyndns properties.
The properties you can configure are:

Property   | Description
---------- | ------------
zoneName   | The name of your main domain (zone) (e.g. *example.org*).
recordName | Name of the subdomain (record) which should point to your public IP (e.g. *home.example.org*).
recordTtl  | Amount in seconds that your DNS entry should live.
myIpUrl    | URL of the 'My IP is' service to use. Has to return a single string as response!
hetznerUrl | URL of the Hetzner DNS API service.

# How to install / start
Clone the repository onto your local machine and execute the following steps to:
1. Build the gradle project.
2. Build the docker image.
3. Start the docker image.

```bash
./gradlew clean distTar
docker build -t hetzner-dyndns .
docker run --env HETZNER_DNS_API_TOKEN=XXX hetzner-dyndns
```

**You have to replace the `HETZNER_DNS_API_TOKEN=XXX` with your own API token.**