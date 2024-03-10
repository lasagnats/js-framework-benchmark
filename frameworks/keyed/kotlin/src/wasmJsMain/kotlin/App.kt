import kotlinx.browser.document
import org.w3c.dom.*


class App(private var rowId: Int = 1) {

    var selectedRowID: String? = null;

    fun buildData(count: Int = 1000): List<Entry> {
        val adjectives: Array<String> = arrayOf(
            "pretty",
            "large",
            "big",
            "small",
            "tall",
            "short",
            "long",
            "handsome",
            "plain",
            "quaint",
            "clean",
            "elegant",
            "easy",
            "angry",
            "crazy",
            "helpful",
            "mushy",
            "odd",
            "unsightly",
            "adorable",
            "important",
            "inexpensive",
            "cheap",
            "expensive",
            "fancy"
        );
        val colours: Array<String> =
            arrayOf("red", "yellow", "blue", "green", "pink", "brown", "purple", "brown", "white", "black", "orange");
        val nouns: Array<String> = arrayOf(
            "table",
            "chair",
            "house",
            "bbq",
            "desk",
            "car",
            "pony",
            "cookie",
            "sandwich",
            "burger",
            "pizza",
            "mouse",
            "keyboard"
        );
        var data: MutableList<Entry> = mutableListOf();
        for (i in 0..<count) {
            data.add(
                Entry(
                    rowId++,
                    adjectives[adjectives.indices.random()] + " " + colours[colours.indices.random()] + " " + nouns[nouns.indices.random()]
                )
            )
        }
        return data
    }

    fun setupListeners() {
        val runBtn = document.getElementById("run");
        val runLotsBtn = document.getElementById("runlots");
        val addBtn = document.getElementById("add");
        val updateBtn = document.getElementById("update");
        val clearBtn = document.getElementById("clear");
        val swapRowsBtn = document.getElementById("swaprows");
        val table = document.getElementById("tbody");

        runBtn?.addEventListener("click") {
            unselect()
            table?.textContent = ""
            val rows: List<Entry> = buildData();
            for (row in rows) {
                table?.appendChild(getTableRow(row))
            }
        }

        addBtn?.addEventListener("click") {
            val rows: List<Entry> = buildData();
            for (row in rows) {
                table?.appendChild(getTableRow(row))
            }
        }

        runLotsBtn?.addEventListener("click") {
            unselect();
            table?.textContent = ""
            val rows: List<Entry> = buildData(10000);
            for (row in rows) {
                table?.appendChild(getTableRow(row))
            }
        }

        clearBtn?.addEventListener("click") {
            unselect();
            table?.textContent = ""
        }

        swapRowsBtn?.addEventListener("click") {

            val el1 = table?.firstChild?.nextSibling;
            val elAfterEl1 = el1?.nextSibling;
            val el2 = table?.childNodes?.get(998);
            val elAfterEl2 = el2?.nextSibling;

            if (el1 != null && el2 != null) {
                table.insertBefore(el2, elAfterEl1)
                table.insertBefore(el1, elAfterEl2)
            }
        }

        updateBtn?.addEventListener("click") {
            val tableRows = table?.childNodes
            if (tableRows != null) {
                for (index in 0..<tableRows.length step 10) {
                    tableRows[index]?.firstChild?.nextSibling?.firstChild?.firstChild?.textContent += " !!!"
                }
            }
        }

    }

    private fun getTableRow(entry: Entry): Element {
        val tr = document.createElement("tr");
        val rowId = "entry" + entry.id.toString();
        tr.id = rowId;
        tr.innerHTML =
            "<td class='col-md-1'></td><td class='col-md-4'><a class='lbl'></a></td><td class='col-md-1'><a class='remove'><span class='remove glyphicon glyphicon-remove' aria-hidden='true'></span></a></td><td class='col-md-6'></td>";
        tr.getElementsByClassName("remove").get(0)?.addEventListener("click") {
            delete(entry.id)
        }
        tr.addEventListener("click") {

            unselect();
            selectedRowID = rowId;
            tr.classList.add("danger")
        }
        val td1 = tr.firstChild;
        val a2 = td1?.nextSibling?.firstChild;

        td1?.textContent = entry.id.toString()
        a2?.textContent = entry.label

        return tr
    }

    private fun delete(index: Int) {
        document.getElementById("entry$index")?.remove()
    }

    private fun unselect() {
        if (selectedRowID != null) {
            document.getElementById(selectedRowID!!)?.classList?.remove("danger")
        }
        selectedRowID = null;
    }
}