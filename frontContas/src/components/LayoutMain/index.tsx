import { Outlet } from "react-router-dom";
import { useState } from "react";
import Header from "../header";
import Sidebar from "../sidebar";
import Footer from "../footer";
import Usuarios from "../../paginas/usuarios/usuarios";
import Contas from "../../paginas/contas/contas";
import ContasEditar from "../../paginas/contas/contasEditar";
import ContasCriar from "../../paginas/contas/contasCriar";


function LayoutMain() {
  const [pagina, setPagina] = useState<{ nome: string; id?: number }>({ nome: "contas" });

  let conteudo;
  switch (pagina.nome) {
    case "contas":
      conteudo = <Contas setPagina={setPagina} />;
      break;
    case "criarcontas":
      conteudo = <ContasCriar setPagina={setPagina} />;
      break;
    case "usuarios":
      conteudo = <Usuarios />;
      break;
    case "editarConta":
      conteudo =
        pagina.id !== undefined ? (
          <ContasEditar id={pagina.id} setPagina={setPagina} />
        ) : (
          <p>Conta não encontrada</p>
        );
      break;
    default:
      conteudo = <p>Página não encontrada</p>;
  }

  return (
    <div className="d-flex flex-column vh-100">
      <Header />
      <div className="d-flex flex-grow-1">
        <Sidebar setPagina={(nome: string) => setPagina({ nome })} />
        <main className="flex-grow-1 p-3 bg-light">
          {conteudo}
        </main>
      </div>
      <Footer />
    </div>
  );
}

export default LayoutMain;
