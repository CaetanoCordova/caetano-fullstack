import LayoutAuth from "./components/LayoutAuth/index.tsx";
import LayoutMain from "./components/LayoutMain/index.tsx";
import Login from "./paginas/login";
import Cadastrese from "./paginas/cadastrese";
import { Navigate, Route, Routes } from "react-router-dom";
import Contas from "./paginas/contas/contas.tsx";
import Usuarios from "./paginas/usuarios/usuarios.tsx";
import ContasEditar from "./paginas/contas/contasEditar.tsx";
import ContasCriar from "./paginas/contas/contasCriar.tsx";
import PrivateRoute from "./components/PrivateRoutes.tsx";
import Recuperacao from "./paginas/recuperacao/index.tsx";
import Codigo from "./paginas/recuperacao/codigo.tsx";

function App() {
  return (
    <Routes>

      <Route path="/auth" element={<LayoutAuth />}>
        <Route path="login" element={<Login />} />
        <Route path="cadastrese" element={<Cadastrese />} />
        <Route path="recuperacao" element={<Recuperacao />} />
        <Route path="codigo" element={<Codigo />} />
      </Route>

      <Route element={<PrivateRoute />}>
        <Route path="/" element={<LayoutMain />}>
          <Route path="contas" element={<Contas />} />
          <Route path="contas/criar" element={<ContasCriar />} />
          <Route path="contas/:id/editar" element={<ContasEditar />} />
          <Route path="usuarios" element={<Usuarios />} />
        </Route>
      </Route>
      <Route path="*" element={<Navigate to="/auth/login" />} />
    </Routes>
  );
}

export default App;