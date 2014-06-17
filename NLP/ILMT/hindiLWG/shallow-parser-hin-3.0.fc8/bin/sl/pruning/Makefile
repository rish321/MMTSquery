#
# Makefile : Builds tokenizer for Linux/Unix.
#

PREFIX=${SHALLOW_PARSER}

# Program files and directories.
DEST_PROG_BIN_DIR = $(PREFIX)/common/pruning

# make all -- make programs, data, library, documentation, etc.
all: 

# make install -- Install what all needs to be installed, copying the files from the packages tree to systemwide directories.
install:
	mkdir -p $(DEST_PROG_BIN_DIR)/data_bin
	cp -fr src API pruning.pl printinput.pl tests $(DEST_PROG_BIN_DIR)
	cp -f data_bin/mapping.db $(DEST_PROG_BIN_DIR)/data_bin/
	cp -f pruning.sh $(DEST_PROG_BIN_DIR)/pruning.sh

# make clean -- cleaning everything that can be automatically recreated with "make".
clean:uninstall
uninstall: 
	rm -fr $(DEST_PROG_BIN_DIR)

.PHONY: all install clean
