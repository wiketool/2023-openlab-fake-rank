# ------------------------------------------------------------------------------
# Cargo Build Stage
# ------------------------------------------------------------------------------

FROM rust:latest as cargo-build
RUN apt-get install debian-archive-keyring 
RUN apt-get update
RUN apt-get install musl-tools -y
RUN rustup target add x86_64-unknown-linux-musl
WORKDIR /usr/src/openlab-fake-rank
COPY Cargo.toml Cargo.toml
COPY src/ src/
RUN RUSTFLAGS=-Clinker=musl-gcc cargo build --release --target=x86_64-unknown-linux-musl
RUN rm -f target/x86_64-unknown-linux-musl/release/deps/openlab-fake-rank*
COPY . .
RUN RUSTFLAGS=-Clinker=musl-gcc cargo build --release --target=x86_64-unknown-linux-musl
# ------------------------------------------------------------------------------
# Final Stage
# ------------------------------------------------------------------------------

FROM alpine:latest

COPY --from=cargo-build /usr/src/openlab-fake-rank/target/x86_64-unknown-linux-musl/release/openlab-fake-rank /usr/local/bin/openlab-fake-rank
CMD ["openlab-fake-rank"]
