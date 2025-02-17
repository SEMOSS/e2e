podman stop semoss

podman rm -f semoss

podman rm -f test

podman network rm tnet

podman network create tnet

podman build --network=host -f Dockerfile -t test

podman run -it  -d --network=tnet --restart=always -p 127.0.0.1:9091:8080 -e SETSOCIAL=true -e ENABLE_NATIVE=true -e ENABLE_NATIVE_REGISTRATION=true --name semoss semoss/docker /bin/bash -c "runCS.sh"

podman run -it -v .:/workspace -w /workspace --network=host --name test test

podman stop semoss