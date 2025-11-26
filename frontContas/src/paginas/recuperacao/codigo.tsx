import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { store } from "../../store/store";
import { CodigoNovo, type CodigoRequest, type CodigoResponse} from "../../services/authService";

function Codigo() {
  const navigator = useNavigate();

  const [formData, setFormData] = useState<CodigoRequest>({
    email:'',
    senha:'',
    token:''
  });

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const {name,value} = event.target;
    setFormData(prevState => ({
      ...prevState,
      [name]:value,
    }))
  }

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {

      const CodigoResponse = await CodigoNovo(formData);

      console.log("codigo");

      const state = store.getState();
      navigator("/auth/login")
    }
  
    catch (error){

    }
  }

  return (
    <form onSubmit={handleSubmit}>
      <p className="text-muted text-center">Insira o código que enviamos para seu e-mail</p>

      <div className="mb-3">
        <label className="form-label">Código...</label>
        <input
          type="text"
          name="codigo"
          id="senha"
          className="form-control"
          value={formData.codigo}
          onChange={handleChange}
          placeholder="...de recuperação. Simples." />
      </div>

      <button type="submit" className="btn btn-primary w-100">Enviar</button>

      <p className="text-center mt-3">
        Voltar para a tela de <Link to="/auth/login">login</Link>
      </p>
    </form>
  );
}

export default Login;
