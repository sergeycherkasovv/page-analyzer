clean:
	make -C app clean

build:
	make -C app build

install:
	make -C app install

run:
	make -C app run

start:
	make -C app start

test:
	make -C app test
.PHONY: build