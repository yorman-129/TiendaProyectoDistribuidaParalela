import { useState, useEffect } from 'react'
import './App.css'
import {  gql, useMutation } from '@apollo/client';
import { Client } from './main';

const ADD_PRODUCT_MUTATION = gql`
  mutation SaveProducto($nombre: String!, $cantidad: Int!) {
    saveProducto(nombre: $nombre, cantidad: $cantidad) {
      nombre
      cantidad
    }
  }
`;
const ADD_CAJERO_MUTATION = gql`
  mutation SaveCajero($nombreEmpleado: String!, ) {
    saveCajero(nombreEmpleado: $nombreEmpleado,) {
      nombreEmpleado
    }
  }
`;



function App() {
  const [cajeros, setCajeros] = useState(null);
  const [productos, setProductos] = useState(null);
  const [form, setForm] = useState(null);
  const [saveProducto] = useMutation(ADD_PRODUCT_MUTATION);
  const [saveCajero] = useMutation(ADD_CAJERO_MUTATION);


  useEffect(() => {
    Client
      .query({
        query: gql`
          query {
            findAllProductos {
              nombre
              cantidad
            }
          }
        `,
      })
      .then((result) => setProductos(result.data.findAllProductos));

      Client
      .query({
        query: gql`
          query {
            findAllCajeros {
              nombreEmpleado
            }
          }
        `,
      })
      .then((result) => setCajeros(result.data.findAllCajeros));
  }, []);

  const informationForm = (event) => {
    const nameInput = event.target.id;
    setForm({
      ...form,
      [nameInput]: event.target.value,
    })
  }

  const handleClick = () => {
    if (form && form.nombreEmpleado) {

      saveCajero({
        variables: {
          nombreEmpleado: form.nombreEmpleado,
        }
      })
        .then(result => {
          console.log('Producto añadido:', result.data.saveCajero);
          // Realiza las acciones adicionales que desees después de añadir el producto
        })
        .catch(error => {
          console.error('Error al añadir el producto:', error);
          // Maneja el error de alguna manera adecuada
        });
    }
    

    if ((form && form.nombre && form.cantidad)) {
      saveProducto({
        variables: {
          nombre: form.nombre,
          cantidad: form.cantidad,
        },
      })
        .then(result => {
          console.log('Producto añadido:', result.data.saveProduct);
          // Realiza las acciones adicionales que desees después de añadir el producto
        })
        .catch(error => {
          console.error('Error al añadir el producto:', error);
          // Maneja el error de alguna manera adecuada
        });
    }
  }

  return (
    <>
    <div className="bienvenida">
      <h1>SISTEMA DE TIENDA</h1>
    </div>
      <div className='dividir'>
        <div className='container-getInformation'>
          <div className="containerTable">
          <h4>cajeros</h4>
          {cajeros && (
            <ul >
              {cajeros.map((cajero) => (
                <div className='divOptions'>{cajero.nombreEmpleado}</div>
              ))}
            </ul>
          )}
          </div>
          <div className="containerTable">
          <h4>productos</h4>
          {productos && (
            <ul>
              {productos.map((producto) => (
                <div className='divOptions' >
                  {producto.nombre} <strong>*</strong> {producto.cantidad}
                </div>
              ))}
            </ul>
          )}
          </div>
        </div>
        <div className="container-forms">
          <form>
            <div className="mb-3">
              <label for="exampleInputEmail1" className="form-label">Cajero</label>
              <input type="text" className="form-control" id="nombreEmpleado" aria-describedby="emailHelp" onChange={informationForm} />
              <div id="emailHelp" className="form-text">Ingresa el nombre del cajero</div>
            </div>
            <div className="mb-3">
              <label for="exampleInputPassword1" className="form-label">Producto</label>
              <input type="text" className="form-control" id="nombre" onChange={informationForm} />
            </div>
            <div className="mb-3">
              <label for="exampleInputPassword1" className="form-label">Cantidad</label>
              <input type="number" className="form-control" id="cantidad" onChange={informationForm} />
            </div>
            
            <button type="submit" className="btn btn-primary" onClick={handleClick}>Submit</button>
          </form>
        </div>
      </div>
    </>
  )
}

export default App
