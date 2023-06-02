package com.example.tienda.exception;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
public class GraphQLErrorAdapter implements GraphQLError{
    private GraphQLError error;

    public GraphQLErrorAdapter(GraphQLError error) {
        this.error = error;
    }
    @Override
    public String getMessage() {
        return error.getMessage();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return error.getLocations();
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }


    @Override
    public List<Object> getPath() {
        return GraphQLError.super.getPath();
    }

    @Override
    public Map<String, Object> toSpecification() {
        return GraphQLError.super.toSpecification();
    }

    @Override
    public Map<String, Object> getExtensions() {
        return GraphQLError.super.getExtensions();
    }
}
