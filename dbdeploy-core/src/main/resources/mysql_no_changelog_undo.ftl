[#ftl]
[#-- @ftlvariable name="changeLogTableName" type="java.lang.String" --]
[#-- @ftlvariable name="delimiter" type="java.lang.String" --]
[#-- @ftlvariable name="separator" type="java.lang.String" --]
[#-- @ftlvariable name="scripts" type="java.util.List<com.dbdeploy.scripts.ChangeScript>" --]
[#list scripts as script]

-- START UNDO OF CHANGE SCRIPT ${script}

START TRANSACTION${separator}${delimiter}

${script.undoContent}

COMMIT${separator}${delimiter}

-- END UNDO OF CHANGE SCRIPT ${script}

[/#list]
