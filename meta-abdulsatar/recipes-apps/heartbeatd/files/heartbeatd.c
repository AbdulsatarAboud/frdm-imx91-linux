// SPDX-License-Identifier: MIT
// heartbeatd: prints a heartbeat line every N seconds until SIGTERM/SIGINT.
#define _POSIX_C_SOURCE 200809L
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

static volatile sig_atomic_t running = 1;

static void on_signal(int sig)
{
	(void)sig;
	running = 0;
}

int main(int argc, char **argv)
{
	unsigned int interval = 10;

	if (argc > 1) {
		char *end;
		unsigned long v = strtoul(argv[1], &end, 10);

		if (*end != '\0' || v == 0 || v > 3600) {
			fprintf(stderr, "usage: %s [seconds 1-3600]\n", argv[0]);
			return 2;
		}
		interval = (unsigned int)v;
	}

	struct sigaction sa = { 0 };
	sa.sa_handler = on_signal;
	sigemptyset(&sa.sa_mask);
	sigaction(SIGTERM, &sa, NULL);
	sigaction(SIGINT, &sa, NULL);

	/* journald reads stdout through a pipe: flush per line. */
	setvbuf(stdout, NULL, _IOLBF, 0);
	printf("heartbeatd started, interval %u s\n", interval);

	unsigned long count = 0;
	while (running) {
		struct timespec ts;

		clock_gettime(CLOCK_MONOTONIC, &ts);
		printf("heartbeat %lu, uptime %ld s\n", ++count, (long)ts.tv_sec);
		sleep(interval); /* returns early when a signal arrives */
	}

	printf("heartbeatd stopping after %lu beats\n", count);
	return 0;
}
