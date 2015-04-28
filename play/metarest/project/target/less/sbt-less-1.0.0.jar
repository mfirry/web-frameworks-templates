/*global process, require */

(function () {

    "use strict";

    var args = process.argv,
        fs = require("fs"),
        less = require("less"),
        mkdirp = require("mkdirp"),
        path = require("path");

    var SOURCE_FILE_MAPPINGS_ARG = 2;
    var TARGET_ARG = 3;
    var OPTIONS_ARG = 4;

    var sourceFileMappings = JSON.parse(args[SOURCE_FILE_MAPPINGS_ARG]);
    var target = args[TARGET_ARG];
    var options = JSON.parse(args[OPTIONS_ARG]);

    var sourcesToProcess = sourceFileMappings.length;
    var results = [];
    var problems = [];

    function parseDone() {
        if (--sourcesToProcess === 0) {
            console.log("\u0010" + JSON.stringify({results: results, problems: problems}));
        }
    }

    function throwIfErr(e) {
        if (e) throw e;
    }

    sourceFileMappings.forEach(function (sourceFileMapping) {

        var input = sourceFileMapping[0];
        var outputFile = sourceFileMapping[1].replace(".less", options.compress ? ".min.css" : ".css");
        var output = path.join(target, outputFile);
        var sourceMapOutput = output + ".map";

        fs.readFile(input, "utf8", function (e, contents) {
            throwIfErr(e);

            var writeSourceMap = function (content) {
                mkdirp(path.dirname(sourceMapOutput), function (e) {
                    throwIfErr(e);
                    fs.writeFile(sourceMapOutput, content, "utf8", throwIfErr);
                });
            };

            var contentWithVars = (options.globalVariables ? options.globalVariables + "\n" : "") +
                contents +
                (options.modifyVariables ? "\n" + options.modifyVariables : "");

            options.filename = input; // Yuk, but I can't be bothered copying as there is no easy way in JS.
            var parser = new (less.Parser)(options);
            parser.parse(contentWithVars, function (e, tree) {
                if (e) {
                    problems.push({
                        message: e.message,
                        severity: "error",
                        lineNumber: e.line,
                        characterOffset: e.column,
                        lineContent: contentWithVars.split("\n")[e.line],
                        source: input
                    });
                    results.push({
                        source: input,
                        result: null
                    });

                    parseDone();

                } else {

                    var css = tree.toCSS({
                        cleancss: options.cleancss,
                        cleancssOptions: options.cleancssOptions || {},
                        compress: options.compress,
                        ieCompat: options.ieCompat || true,
                        maxLineLen: options.maxLineLen,
                        outputSourceFiles: options.outputSourceFiles,
                        relativeUrls: options.relativeUrls,
                        rootpath: options.rootPath || "",
                        silent: options.silent,
                        sourceMap: options.sourceMap,
                        sourceMapBasepath: path.dirname(input),
                        sourceMapFilename: path.basename(sourceMapOutput),
                        sourceMapOutputFilename: path.basename(outputFile),
                        strictMath: options.strictMath,
                        strictUnits: options.strictUnits,
                        urlArgs: options.urlArgs || "",
                        verbose: options.verbose,
                        writeSourceMap: writeSourceMap
                    });

                    mkdirp(path.dirname(output), function (e) {
                        throwIfErr(e);

                        fs.writeFile(output, css, "utf8", function (e) {
                            throwIfErr(e);

                            var imports = [];
                            var files = parser.imports.files;
                            for (var file in files) {
                                if (files.hasOwnProperty(file)) {
                                    imports.push(file);
                                }
                            }

                            results.push({
                                source: input,
                                result: {
                                    filesRead: [input].concat(imports),
                                    filesWritten: options.sourceMap ? [output, sourceMapOutput] : [output]
                                }
                            });

                            parseDone();
                        });
                    });
                }
            });
        });
    });
})();