#
# Makefile : Builds headcomputation for Linux/Unix.
#

PREFIX=${SHALLOW_PARSER}

# Program files and directories.
DEST_PROG_BIN_DIR = $(PREFIX)/common/convertor

# make all -- make programs, data, library, documentation, etc.
all: 

# make install -- Install what all needs to be installed, copying the files from the packages tree to systemwide directories.
install:
	mkdir -p $(DEST_PROG_BIN_DIR)
	cp -fr bin API convertor.pl iscii2utf8.py printinput.pl tests $(DEST_PROG_BIN_DIR)/
	cp -f convertor_utf2wx_text_hin.sh convertor_wx2utf_ssf_hin.sh $(DEST_PROG_BIN_DIR)/

# make clean -- cleaning everything that can be automatically recreated with "make".
clean:uninstall
uninstall: 
	rm -fr $(DEST_PROG_BIN_DIR)

.PHONY: all install clean
