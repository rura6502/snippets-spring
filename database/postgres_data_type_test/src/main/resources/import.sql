

-- for jsonb test
CREATE TABLE public.tbl_test_json (
	id varchar NOT NULL,
	test_jsonb jsonb NULL,
  test_json json NULL,
	CONSTRAINT tbl_process_model_request_pk PRIMARY KEY (id)
);