/*global process, require */

/*
 * Lint a number of files.
 *
 * Arguments:
 * 0 - name given to the command invoking the script (unused)
 * 1 - filepath of this script (unused)
 * 2 - array of file paths to the files to lint
 * 3 - the target folder to write to (unused - not required)
 * 4 - jshint options as a Json object
 *
 * Json array tuples are sent to stdout for each file in error (if any). Each tuple is an array where the
 * element 0 corresponds to the file path of the file linted, and element 1 is JSHINT.errors.
 */

(function () {

    "use strict";

    var args = process.argv;
    var console = require("console");
    var fs = require("fs");
    var jshint = require("jshint");

    var SOURCE_FILE_MAPPINGS_ARG = 2;
    var OPTIONS_ARG = 4;

    var options = JSON.parse(args[OPTIONS_ARG]);

    var sourceFileMappings = JSON.parse(args[SOURCE_FILE_MAPPINGS_ARG]);
    var sourceFilesToProcess = sourceFileMappings.length;
    var results = [];
    var problems = [];
    sourceFileMappings.forEach(function (sourceFilePath) {
        var sourceFile = sourceFilePath[0];
        fs.readFile(sourceFile, "utf8", function (e, source) {
            if (e) {
                console.error("Error while trying to read " + source, e);
            } else {
                jshint.JSHINT(source, options);
                var actualErrors = 0;
                jshint.JSHINT.errors.forEach(function (e) {
                    if (e) {
                        problems.push({
                            message: e.reason,
                            severity: (e.id? e.id.substring(1, e.id.length - 1) : "error"),
                            lineNumber: e.line,
                            characterOffset: e.character - 1,
                            lineContent: e.evidence,
                            source: sourceFile
                        });
                        ++actualErrors;
                    }
                });
                results.push({
                    source: sourceFile,
                    result: (actualErrors === 0 ? {filesRead: [sourceFile], filesWritten: []} : null)
                });
            }
            if (--sourceFilesToProcess === 0) {
                console.log("\u0010" + JSON.stringify({results: results, problems: problems}));
            }
        });
    });
}());
