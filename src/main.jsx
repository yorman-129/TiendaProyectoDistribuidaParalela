import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import { ApolloClient, InMemoryCache, ApolloProvider } from '@apollo/client';

export const Client = new ApolloClient({
  uri: 'http://localhost:8087/graphql',
  cache: new InMemoryCache(),
});


ReactDOM.createRoot(document.getElementById('root')).render(
    
  <ApolloProvider client={Client}>
    <App />
    </ApolloProvider>

);

