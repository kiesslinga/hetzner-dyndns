FROM adoptopenjdk:11-jre-openj9

# Copy .tar archive into container
RUN mkdir /opt/app
COPY build/distributions/hetzner-dyndns.tar /opt/app

# Extract archive
WORKDIR /opt/app
RUN tar xvf /opt/app/hetzner-dyndns.tar

# Run application
WORKDIR /opt/app/hetzner-dyndns
CMD ["./bin/hetzner-dyndns"]
