(ns conversor.core
  (:require	 [clojure.tools.cli :refer [parse-opts]]
             [clj-http.client :as http-client]
             [cheshire.core	:refer	:all])
  (:gen-class))

(def opcoes-do-programa
  [["-d" "--de moeda base" "moeda base para conversão" :default "eur"]
   ["-p" "--para moeda destino" "moeda a qual queremos saber o valor"]])

(def chave "580b40c874d7baf4e2a8")
;;	aqui	vai	a	chave	que	você	recebeu	por	email
;;	essa	daqui	não	é	válida.	é	um	exemplo	de	como	se	parece

(def api-url "https://free.currencyconverterapi.com/api/v6/convert")
;;	o	enderaço	da	API

(defn	parametrizar-moedas	[de	para]
  (str de "_" para))

(defn	-main
  [& args]
  (let [{:keys [de	para]} (:options
                             (parse-opts args opcoes-do-programa))]

    ;;	aqui	acontece	um	_destructuring_,	pegando	os	valores
    ;;	em	`:de`	e	`:para`	oriundos	das	opções	da	linha	de	comando
    (prn "Cotação:"	(http-client/get api-url
                                       {:query-params	{"q" (parametrizar-moedas	de para)
                                                        "apiKey" chave}}))))