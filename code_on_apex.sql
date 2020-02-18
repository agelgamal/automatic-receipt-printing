BEGIN
DBMS_SCHEDULER.SET_JOB_ARGUMENT_VALUE (
   job_name                => 'BSS_ERP.ISA_WORKING',
   argument_position       => 3,
   argument_value          => :P351_HSALES_CUST_ID);

DBMS_SCHEDULER.RUN_JOB(
    JOB_NAME            => 'BSS_ERP.ISA_WORKING',
    USE_CURRENT_SESSION => TRUE);
END;