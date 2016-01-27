package com.dbdeploy.scripts;

import com.dbdeploy.exceptions.UnrecognisedFilenameException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DirectoryScanner {
	
	private final FilenameParser filenameParser;
	private final String encoding;
	
	public DirectoryScanner(String encoding, FilenameParser filenameParser) {
        this.encoding = encoding;
        this.filenameParser = filenameParser;
    }
	
	public List<ChangeScript> getChangeScriptsForDirectory(File directory)  {
		try {
			System.err.println("Reading change scripts from directory " + directory.getCanonicalPath() + "...");
		} catch (IOException e1) {
			// ignore
		}

		List<ChangeScript> scripts = new ArrayList<ChangeScript>();
		
		for (File file : directory.listFiles()) {
			if (file.isFile()) {
				String filename = file.getName();
				try {
					long id = this.filenameParser.extractIdFromFilename(filename);
					scripts.add(new ChangeScript(id, file, encoding));
				} catch (UnrecognisedFilenameException e) {
					// ignore
				}
			}
		}
		
		return scripts;

	}

}
