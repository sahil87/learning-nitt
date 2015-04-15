################################################################################
# Automatically-generated file. Do not edit!
################################################################################

ROOT := ..

-include $(ROOT)/makefile.init

RM := rm -rf

# All of the sources participating in the build are defined here
-include sources.mk
-include $(SUBDIRS:%=%/subdir.mk)
-include objects.mk
ifneq ($(strip $(DEPS)),)
-include $(DEPS)
endif

-include $(ROOT)/makefile.defs

# Add inputs and outputs from these tool invocations to the build variables 

# All Target
all: pre-build main-build

# Main-build Target
main-build: libcppgui.so

# Tool invocations
libcppgui.so: $(OBJS) $(USER_OBJS)
	@echo 'Building target: $@'
	@echo 'Invoking: GCC C Linker'
	@echo gcc -shared -olibcppgui.so $(OBJS) $(USER_OBJS) $(LIBS)
	@gcc -shared -olibcppgui.so $(OBJS) $(USER_OBJS) $(LIBS)
	@echo 'Finished building target: $@'
	@echo ' '

# Other Targets
clean:
	-$(RM) $(DEPS)$(OBJS)$(LIBRARIES) libcppgui.so
	-@echo ' '

pre-build:
	-qmake
	-@echo ' '

.PHONY: all clean dependents
.SECONDARY: main-build pre-build

-include $(ROOT)/makefile.targets
