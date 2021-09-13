package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityDetalhesProdutoBinding
import br.com.alura.orgs.extensions.formataParaMoedaBrasileira
import br.com.alura.orgs.extensions.tentaCarregarImagem
import br.com.alura.orgs.model.Produto
import kotlin.concurrent.thread

class DetalhesProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }
    private var id: Int = 0
    private val produtoDao by lazy {
        AppDatabase.criaBanco(this)
            .produtoDao()
    }
    private var produto: Produto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_detalhes_editar -> {
                vaiParaFormulario()
            }
            R.id.menu_detalhes_remover -> {
                remove()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun vaiParaFormulario() {
        Intent(
            this,
            FormularioProdutoActivity::class.java
        ).apply {
            produto?.let {
                putExtra(CHAVE_PRODUTO_ID, it.id)
                startActivity(this)
            }
        }
    }

    private fun remove() {
        thread {
            produto?.let(produtoDao::remove)
            finish()
        }
    }

    private fun tentaCarregarProduto() {
        id = intent.getIntExtra(CHAVE_PRODUTO_ID, 0)
        produtoDao.carregaProduto(id).observe(this) {
            it?.let { produtoEncontrado ->
                produto = produtoEncontrado
                preencheCampos(produtoEncontrado)
            } ?: finish()
        }
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            activityDetalhesProdutoImagem.tentaCarregarImagem(produtoCarregado.imagem)
            activityDetalhesProdutoNome.text = produtoCarregado.nome
            activityDetalhesProdutoDescricao.text = produtoCarregado.descricao
            activityDetalhesProdutoValor.text =
                produtoCarregado.valor.formataParaMoedaBrasileira()
        }
    }

}